#!/bin/sh

for file in `find . -name "*.java"`; do

  count=`grep -c "Copyright \(C\)" $file`

  if [ $count -eq 0 ]; then
    tmp=`basename $file`

    cp $file $tmp

    cat copyright.txt $tmp > $file

    rm -f $tmp

    echo $file
  fi

done
