#!/bin/bash

set -e
echo "1"
export service_name=ens-service
export application_pkg=com.ens
export app_path=/etc/conf/ens/$service_name

#rm -rf $app_path/bootstrap.properties

#echo "spring.application.name=$service_name" >> $app_path/bootstrap.properties
#echo "spring.cloud.consul.host=$consul_url" >> $app_path/bootstrap.properties
#echo "spring.cloud.consul.port=$consul_port" >> $app_path/bootstrap.properties

export jvm_options=" $jvm_options -Dfile.encoding=UTF-8 -Dspring.config.location=$app_path/ -Dlogging.config=$app_path/logback-spring.xml -Dlogfile.location=/var/log/ens/$service_name.log";

echo "Staring $service_name"

exec java $jvm_options -jar ens.jar
