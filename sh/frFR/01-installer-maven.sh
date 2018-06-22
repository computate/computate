#!/bin/bash
source "$(dirname $0)/00-functions.sh"

#########
# maven #
#########

if hash apt-get 2>/dev/null ; then
	computate "sudo apt-get build-dep -y maven"
	computate "sudo apt-get install -y maven"
fi
if hash yum 2>/dev/null ; then
	computate "sudo yum-builddep -y maven"
	computate "sudo yum install -y maven"
fi
computate "sudo mkdir /usr/local/src/maven /opt/maven-$VERSION_MAVEN"
computate "sudo chown $USER: /usr/local/src/maven /opt/maven-$VERSION_MAVEN"
computate "git clone https://gitbox.apache.org/repos/asf/maven.git /usr/local/src/maven"
computate "cd /usr/local/src/maven; git checkout maven-$VERSION_MAVEN"
computate "cd /usr/local/src/maven; mvn -DdistributionTargetDir='/tmp/maven-$VERSION_MAVEN' clean package"
computate "sudo mv /tmp/maven-$VERSION_MAVEN /opt/"
computate "sudo ln -s /opt/maven-$VERSION_MAVEN/bin/mvn /usr/local/bin/mvn"