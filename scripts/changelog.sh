#!/bin/bash
# Generates changelog day by day
NEXT=$(date +%F)
echo "# CHANGELOG"
echo "---"
git log --format="%cd" --date=short | sort -u -r | head -n 20 | while read DATE ; do
    echo
    echo '## '$DATE
    git log --date=iso --format="> +  %s %N (*par [%cN](%ce) à ts%ct [view commit](https://gitlab.com/qaobee/qaobee-swarn/commit/%H)*)" --since=$DATE --until=$NEXT | while read LOG; do
        BEGIN=$(echo $LOG | sed -rn 's/(^.*)\ ts[0-9]{10}\ .*$/\1/p')
        TIMESTAMP=@$(echo $LOG | sed -rn 's/^.*\ ts([0-9]{10})\ .*$/\1/p')
        TIME=$(date -d $TIMESTAMP +%H:%M)
        END=$(echo $LOG | sed -rn 's/^.*\ ts[0-9]{10}\ (.*)$/\1/p')
        echo "$BEGIN $TIME $END"
    done
    NEXT=$DATE
done
