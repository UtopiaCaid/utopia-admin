
aws cloudformation deploy --region us-east-2 --stack-name UtopiaAdminMS \
--template-file test-utopia-cftemplate --parameter-overrides ApplicationName=UtopiaAdminMS \
ECRepositoryUri=$AWS_ID/utopia-admin:$COMMIT_HASH DBUrl=$DB_URL DBUsername=$DB_USERNAME \
DBPassword=$DB_PASSWORD ECSCluster=$UTOPIA_CLUSTER ExecutionRoleArn=$EXECUTION_ROLE_ARN \
SubnetID=$UTOPIA_PRIVATE_SUBNET_1 TargetGroupArnDev=$TARGETGROUP_UTOPIA_ADMIN_DEV_ARN \
TargetGroupArnProd=$TARGETGROUP_UTOPIA_ADMIN_PROD_ARN VpcId=$UTOPIA_PUBLIC_VPC_ID \
--capabilities "CAPABILITY_IAM" "CAPABILITY_NAMED_IAM"