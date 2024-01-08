package com.my.pro;


public class Architect {
    /*                                          Lieder Broker (Master)
     *                                              Slave1, Slave2
     *
     *                HR         topic: questions-hr      Async                                      Dev
     *        Sender (Producer) >-------------------> Broker Kafka >------------------------> Notifier (Consumer)
     *                  SQL                                            topic: answer-dev
     *         MySQL <------< RegistrationDB <------< Broker Kafka <------------------------< Statistic
     *
     *
     *   KafkaTemplate.send("questions-hr", "message")    offset                             Acknowledge
     *      ListableFuture<MetaData> future              3,4,5,6
     *   new Callback()->                                                @KafkaListener("topics=questions-hr", "groupId=developers")
     *                                                                   void receive(String msg, Acknowledgement ack){
     *    onSuccess ->log.info()                                         ... processing message ...
     *    onError -> log.error, log.warn, debug                          ack.acknowledge();
     *    or run retry                                                   }
     *
     *
     * */
}