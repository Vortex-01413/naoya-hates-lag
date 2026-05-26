#!/bin/sh

git config --global --add safe.directory /storage/emulated/0/naoya_hates_lag_v2
git add .
git commit -m "feat: full rewrite - profiles, all optimizations, tabbed UI"
git push
echo "PUSHED TO GITHUB"

# Self delete all scripts
rm -f part1.sh part2.sh part3.sh
echo "Scripts deleted"
