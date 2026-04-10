// src/app/interceptors/token.interceptor.ts

import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

/**
 * Interceptor que agrega automáticamente el token JWT a todas las solicitudes HTTP
 */
export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);       // Inyecta el servicio de autenticación
  const token = authService.getToken();          // Obtiene el JWT del localStorage

  if (token) {
    // Clona la petición y agrega el header Authorization
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  // Si no hay token, continúa con la petición original
  return next(req);
};
