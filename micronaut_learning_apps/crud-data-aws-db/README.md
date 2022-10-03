## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)


## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


## Feature data-jdbc documentation

- [Micronaut Data JDBC documentation](https://micronaut-projects.github.io/micronaut-data/latest/guide/index.html#jdbc)



1) install mysql-client, aws cli, jq
2) set aws access key and secret env variables
3) create aws iam user, configure aws cli to use
4) optional(create security groups)
5) create mysql rds on aws
6) make sure to have security group set to db (using vpc-00ea68da046480a6b )
7) get db instance and save to MYSQL_HOST env variable
8) create db user
9) update env variables

## Install MySQL-client

-client is only the cli-
-need to have aws env variables set too

```bash
export AWS_ACCESS_KEY_ID=AKIASC5Z6WSK3YUJHKLC;
export AWS_SECRET_ACCESS_KEY=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
```

- hosts need to be set to work- use after setting up db instance

```bash
export MYSQL_HOST=$(aws rds describe-db-instances --query 'DBInstances[?DBInstanceIdentifier==`crud-data-aws-db-create-micro-person`].Endpoint.Address' --output text) 
```

#### custom env variables add to .yml if choos

```bash
export JDBC_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut
export JDBC_USER=admin
export JDBC_PASSWORD=secret99
```

#### micronaut specific env variables

- specific to micronaut
- connection pools https://micronaut-projects.github.io/micronaut-sql/latest/guide/#jdbc-connection-pools
-

```bash
export DATASOURCES_DEFAULT_URL=jdbc:mysql://${MYSQL_HOST}:3306/micronaut
export DATASOURCES_DEFAULT_USERNAME=admin
export DATASOURCES_DEFAULT_PASSWORD=secret99
```

> Micronaut Framework populates the properties datasources.default.url, datasources.default.username and datasources.default.password with those environment variables' values. Learn more about JDBC Connection Pools.
>
>You can run the application and test the API as it was described in the previous sections. However, when you run the application, Micronaut Test Resources does not start a MySQL container because you have provided values for datasources.default.*
> properties.

## AWS tidbits

> get db instances

```bash
aws rds describe-db-instances \                                                  
  --filters "Name=engine,Values=mysql" \                  
  --query "*[].[DBInstanceIdentifier,Endpoint.Address,Endpoint.Port,MasterUsername]"
```

> configure

```bash
aws configure
```

#### create IAM user

```bash
aws iam create-group --group-name Administrators
aws iam create-user --user-name Administrator
aws iam add-user-to-group --user-name Administrator --group-name Administrators
aws iam attach-group-policy --group-name Administrators --policy-arn $(aws iam list-policies --query 'Policies[?PolicyName==`AdministratorAccess`].{ARN:Arn}' --output text)
aws iam create-access-key --user-name Administrator
```

#### Create VPC, security group, subnets and subnet group (optional) with jq (https://stedolan.github.io/jq/)

```bash
# VPC, internet gateway and route table
export VPC_ID=$(aws ec2 create-vpc --cidr-block 10.0.0.0/16 | jq -r '.Vpc.VpcId')
export IG_ID=$(aws ec2 create-internet-gateway | jq -r '.InternetGateway.InternetGatewayId')
aws ec2 attach-internet-gateway --internet-gateway-id $IG_ID --vpc-id $VPC_ID
aws ec2 modify-vpc-attribute --enable-dns-hostnames --vpc-id $VPC_ID
export RT_ID=$(aws ec2 describe-route-tables --filters "Name=vpc-id,Values=$VPC_ID" --query "RouteTables[].RouteTableId" --output text)
aws ec2 create-route --route-table-id $RT_ID --destination-cidr-block 0.0.0.0/0 --gateway-id $IG_ID

# Security group
aws ec2 create-security-group --group-name crud-data-aws-db-create-micro-person-sg --description "Security Group for the Micronaut MySQL guide" --vpc-id $VPC_ID
export SG_ID=$(aws ec2 describe-security-groups --query 'SecurityGroups[?GroupName==`crud-data-aws-db-create-micro-person-sg`].GroupId' --output text)
aws ec2 authorize-security-group-ingress --group-id $SG_ID --protocol tcp --port 3306 --cidr $(curl ifconfig.me)/32

# Subnets and subnet group
export AZ_0=$(aws ec2 describe-availability-zones --filters "Name=state,Values=available" --query "AvailabilityZones[0].ZoneName" --output text)
export AZ_1=$(aws ec2 describe-availability-zones --filters "Name=state,Values=available" --query "AvailabilityZones[1].ZoneName" --output text)
export SN0_ID=$(aws ec2 create-subnet --vpc-id $VPC_ID --cidr-block 10.0.0.0/20 --availability-zone $AZ_0 | jq -r '.Subnet.SubnetId')
export SN1_ID=$(aws ec2 create-subnet --vpc-id $VPC_ID --cidr-block 10.0.16.0/20 --availability-zone $AZ_1 | jq -r '.Subnet.SubnetId')
aws ec2 modify-subnet-attribute --subnet-id $SN0_ID --map-public-ip-on-launch
aws ec2 modify-subnet-attribute --subnet-id $SN1_ID --map-public-ip-on-launch
aws rds create-db-subnet-group --db-subnet-group-name crud-data-aws-db-create-micro-person-sng --db-subnet-group-description "DB subnet group for the Micronaut MySQL guide" --subnet-ids "$SN0_ID" "$SN1_ID"
```

#### create mysql db instance

```bash
aws rds create-db-instance \
    --db-instance-identifier crud-data-aws-db-create-micro-person \
    --db-instance-class db.t2.micro \
    --engine mysql \
    --master-username admin \
    --master-user-password secret99 \
    --allocated-storage 20 \
    --publicly-accessible \
#    --db-subnet-group-name crud-data-aws-db-create-micro-person-sng \
#    --vpc-security-group-ids $SG_ID \
```

#### wait for instance

```bash
aws rds wait db-instance-available --db-instance-identifier crud-data-aws-db-create-micro-person
```

#### stop aws instance

```bash
aws rds delete-db-instance --db-instance-identifier crud-data-aws-db-create-micro-person --skip-final-snapshot
aws rds wait db-instance-deleted --db-instance-identifier crud-data-aws-db-create-micro-person
```

###### remove security ec2 instances

```bash
aws ec2 delete-subnet --subnet-id $SN0_ID
aws ec2 delete-subnet --subnet-id $SN1_ID
aws rds delete-db-subnet-group --db-subnet-group-name crud-data-aws-db-create-micro-person-sng
aws ec2 delete-security-group --group-id $SG_ID
aws ec2 detach-internet-gateway --internet-gateway-id $IG_ID --vpc-id $VPC_ID
aws ec2 delete-internet-gateway --internet-gateway-id $IG_ID
aws ec2 delete-vpc --vpc-id $VPC_ID
```

## MYSQL

#### create db and user

> Create the database. You can use any valid database name (e.g., micronaut):
>
> CREATE DATABASE micronaut;
> Create a database user. You can use any valid MySQL username (e.g., guide_user) and any valid password:
>
> CREATE USER 'guide_user' IDENTIFIED BY 'M1cr0n4ut!';
> Grant access to the database for the new user:
>
> GRANT ALL ON micronaut.* TO 'guide_user';

#### connecting to aws rds
```bash
mysql -h $MYSQL_HOST -P 3306 -u admin -p
```

## Testing

#### add an object

```bash
curl -X "POST" "http://localhost:8082/person/" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{ "firstName": "itchy", "lastName": "peterson" }'
```

#### list

````bash
curl http://localhost:8082/person/
````

````bash
curl http://localhost:8082/person/record
````