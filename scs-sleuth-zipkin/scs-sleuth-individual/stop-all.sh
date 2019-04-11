#!/bin/sh

lsof -i:9091 | awk 'NR!=1{print $2}' | xargs kill -9;
lsof -i:9092 | awk 'NR!=1{print $2}' | xargs kill -9;
lsof -i:9093 | awk 'NR!=1{print $2}' | xargs kill -9;
lsof -i:9094 | awk 'NR!=1{print $2}' | xargs kill -9;