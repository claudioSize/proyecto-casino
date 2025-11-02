Objetivo del sistema: permitir a usuarios registrarse, depositar/retirar dinero, jugar distintos juegos ,ver historial y saldos 

Tecnologías recomendadas: Java 17+, Spring Boot, Spring Security, Spring Data JPA 

Prioridad MVP: registro/login, wallet (depósito simulado), 1 juego (por ejemplo slots), apuestas/resolución, historial, balance, seguridad básica, tests. 

Requisitos funcionales (orientados al cliente / usuario) 

RF-1 Registro y  

Registro con email + contraseña, verificación de email. 

RF-2 Login  

RF-3 

Recuperación de contraseña. 

RF-4 

Wallet / Billetera 

Visualizar saldo disponible, historial de transacciones. 

RF-5 

Depósito (integración con pasarela de pago o simulador para pruebas). 

Medio de pago 

Saldo 

Transaccion 

RF-6 

Retiro (solicitud y procesamiento). 

RF-7 

Transferencias internas (por ejemplo entre jugadores en juegos sociales) — opcional. 

RF-8 

Juegos 

 

Ver catálogo de juegos con meta (ej: slots, ruleta, blackjack). 

Ingresar a un juego, apostar, jugar, recibir resultado y pago. 

Mostrar reglas y pagos (payout table) por juego. 

Apuestas / Rondas 

Crear ronda, aceptar apuesta(s), ejecutar mecánica RNG, calcular resultado, actualizar wallet atómico. 

Mostrar historial por ronda (apuesta, resultado, payout). 

Historial y recibos 

Historial de apuestas, transacciones y sesiones. 


Modelos de datos (entidades clave) 

User entity: id, email, passwordHash, rol 

User: id, email, passwordHash, displayName, country, kycStatus, createdAt, lastLogin  

Wallet: id, userId (1:1), availableBalance, lockedBalance, currency  

Transaction: id, walletId, type (DEPOSIT, BET, WIN, WITHDRAW), amount, balanceBefore, balanceAfter, createdAt  
Game: id, name, type 

Round / GameSession: id, gameId, userId, stake, outcome, payout, state, createdAt, resolvedAt  

Bet: id, roundId, userId, selection, amount, createdAt 

RNGLog: id, roundId, seed, randomValue, proof (if verifiable), createdAt  
