#!/usr/bin/env bash

./site gen           &&
git add .            &&
git commit -m "$1"
git push
