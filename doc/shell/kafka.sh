#! /bin/bash
if(($#==0));then
  echo -e "start  启动kafka集群；\nstop  关闭kafka集群；"&&exit
fi


case $1 in
  "start")
    for host in hadoop103 hadoop102 hadoop104
      do
        echo "========== $1 $host kafka =========="
        ssh $host "/opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
      done
  ;;
  "stop")
    for host in hadoop103 hadoop102 hadoop104
      do
        echo "========== $1 $host kafka =========="
        ssh $host "/opt/module/kafka/bin/kafka-server-stop.sh "
      done
  ;;
  *)
    echo -e "start  启动kafka集群；\nstop  关闭kafka集群；"&&exit
  ;;
esac
