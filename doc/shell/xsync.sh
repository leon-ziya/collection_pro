#!/bin/bash
# 判断参数个数
if [ $# -lt 1 ]
then
  echo "参数个数有误，请重新输入！" && exit
fi

# 遍历所有节点
for host in hadoop102 hadoop103 hadoop104
  do
    echo "==================== $host ===================="
    # 遍历所有目录，挨个发送
    for file in $@
    do
      # 判断文件是否存在
      if [ -e $file ]
        then
          # 获取父目录
          pdir = $(cd -P $(dirname $file); pwd)
          # 获取当前文件名称
          fname = $(basename $file)
          ssh $host "mkdir -p $pdir"
          rsync -av $pdir/$fname $host:$pdir
        else
          echo "$file 文件不存在！"
      fi
    done
  done
