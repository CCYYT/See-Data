var client;
var TOPIC='8266';//订阅主题
$(function (){
    const clientId = 'mqttjs_' + Math.random().toString(16).substr(2, 8)

    const host = 'ws://139.159.153.42:8083/mqtt'
    const options = {
        keepalive: 60,
        clientId: clientId,
        username:'8266',
        password:'Qq000000',
        protocolId: 'MQTT',
        protocolVersion: 4,
        clean: true,
        reconnectPeriod: 1000,
        connectTimeout: 30 * 1000,
        will: {
            topic: 'WillMsg',
            payload: 'Connection Closed abnormally..!',
            qos: 0,
            retain: false
        },
    }

    client = mqtt.connect(host, options)

    // 连接错误时
    client.on('error', (err) => {
        console.log('Connection error: '+err);
        client.end()
    })

    // 重连时
    client.on('reconnect', () => {
        console.log('Reconnecting...');
    })

    // 连接成功时
    client.on('connect', () => {
        console.log('Client connected:' + clientId);
        // 订阅主题
        client.subscribe(TOPIC, { qos: 0 })
    })

    // 收到消息时
    client.on('message', (topic, message, packet) => {
        var msg = JSON.parse(message);
        $("#album-info__name").text(msg.name);
        $("#album-info__track").text(msg.track);
    })
})

// 发布消息
function publish(msg){
    // Publish
    client.publish(TOPIC, msg, { qos: 0, retain: false });
}
