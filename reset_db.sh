#!/bin/bash
cat /dev/null > reset.sql
cat drop.sql create.sql > reset.sql
mysql -h mysql-user.stanford.edu -u ccs108tjsavage --password=ieyeikoo < reset.sql
