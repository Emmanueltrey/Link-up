package com.chat.linkup

import io.ktor.server.application.*  
import io.ktor.server.engine.*  
import io.ktor.server.netty.*  
import io.ktor.server.routing.*  
import io.ktor.server.websocket.*  
import io.ktor.websocket.*  
import java.time.Duration  

fun main() {  
    embeddedServer(Netty, port = 8080) {  
        install(WebSockets) {  
            pingPeriod = Duration.ofSeconds(15)  
        }  
        routing {  
            webSocket("/chat") {  
                send("You're connected to the chat!")  
                for (frame in incoming) {  
                    if (frame is Frame.Text) {  
                        val text = frame.readText()  
                        send("Server received: $text")  
                    }  
                }  
            }  
        }  
    }.start(wait = true)  
}
