#!/bin/bash

sudo -u postgres psql -d postgres -c "CREATE DATABASE test"
sudo -u postgres psql -d test -f init.sql

