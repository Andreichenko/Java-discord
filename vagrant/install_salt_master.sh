#!/bin/sh

yum -y install git
semanage permissive -a httpd_t
salt-call grains.set env sandbox --local
salt-call grains.setval roles "[salt, bastion]" --local
salt-call state.highstate --local --file-root=/srv/saltstack/salt_master --refresh-grains-cache
salt-call state.highstate --refresh-grains-cache
semanage permissive -a httpd_t