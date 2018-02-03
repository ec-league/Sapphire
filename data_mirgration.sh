#!/usr/bin/env bash

mysqldump -uroot -proot --databases sapphire > sapphire.sql

tar -zcvf sapphire.tar.gz sapphire.sql

scp sapphire.tar.gz root@118.31.14.134:/root/data/

rm sapphire.sql

rm sapphire.tar.gz

ssh root@118.31.14.134 "tar -zxvf /root/data/sapphire.tar.gz"

#ssh root@118.31.14.134 "mysql -uroot -proot < /root/data/sapphire.sql"
