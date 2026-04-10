# 🚀 Proyecto: Spring Boot + Angular + OAuth2 (Google) + JWT

## 📘 Descripción
Este proyecto implementa un sistema de autenticación moderno que permite iniciar sesión con **Google OAuth2**, generar un **JWT (JSON Web Token)** en el backend con **Spring Boot**, y usarlo para proteger las rutas del frontend desarrollado en **Angular**.  
El flujo completo asegura la comunicación segura entre ambas partes, permitiendo acceder al perfil del usuario autenticado.

---

## 🎯 Objetivo
- Permitir autenticación mediante **Google OAuth2**.
- Generar un **JWT** desde el backend una vez validado el login.
- Guardar el token en el **LocalStorage** del navegador.
- Proteger las rutas con un **AuthGuard** en Angular.
- Consumir datos del usuario autenticado desde un endpoint protegido (`/profile`).

---

## 🧠 Arquitectura general

**Flujo resumido:**
1. El usuario hace clic en “Iniciar sesión con Google”.
2. Angular redirige a la URL de autenticación de Google.
3. Google devuelve un `code` a Spring Boot.
4. Spring Boot genera un **JWT** y redirige al frontend (`http://localhost:4200/profile`).
5. Angular guarda el token y permite el acceso al perfil.

---

## ⚙️ Dependencias principales

### 🖥️ Backend (Spring Boot)
```xml
<dependencies>
    <!-- Web y seguridad -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- OAuth2 Client -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>

    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Base de datos (opcional) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
</dependencies>
