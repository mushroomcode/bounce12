package com;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Create by Joyyue sheting on 2020/5/5
 */
public class zookeeperMain {

    private String filename;
    private String exec;
    private ZooKeeper zk;
//    private DataMoni dm;

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err
                    .println("USAGE: Executor hostPort znode filename program [args ...]");
            System.exit(2);
        }

        String hostPort = "192.168.199.15";
        String znode = args[1];
        String filename = args[2];
        String exec[] = new String[args.length - 3];
        System.arraycopy(args, 3, exec, 0, exec.length);
        try {
//            new zookeeperMain(hostPort, znode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public zookeeperMain(String hostPort, String znode, String filename, String exec[]) throws KeeperException, IOException {
        this.filename = filename;
//        this.exec = exec;
//        zk = new ZooKeeper(hostPort, 3000, this);
//        dm = new DataMonitor(zk, znode, null, this);
    }

//    public void run() {
//        try {
//            synchronized (this) {
//                while (!dm.dead) {
//                    wait();
//                }
//            }
//        } catch (InterruptedException e) {
//
//        }
//    }

}
