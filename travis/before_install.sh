
#!/bin/bash

# prepare database for test

mysql -e 'CREATE DATABASE IF NOT EXISTS sapphire;'
mysql -e 'USE sapphire;'

mysql -e 'source ../input.sql'