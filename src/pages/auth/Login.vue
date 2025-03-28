<template>
  <div class="auth-container">
    <Card class="auth-form">
      <template #title>Вход в ваш лучший банк</template>
      <template #content>
        <Form @submit="handleSubmit">
          <div class="form-group">
            <InputText
              v-model="login"
              id="login"
              placeholder="Введите ваш логин"
              required
              class="input-field"
            />
          </div>

          <div class="form-group">
            <Password
              v-model="password"
              id="password"
              placeholder="Введите ваш пароль"
              toggleMask
              required
              class="input-field"
              style="width: 100%"
            />
          </div>

          <div style="display: flex; align-items: center;">
            <Checkbox v-model="rememberMe" binary />
            <label for="rememberMe" style="margin-left: 0.5rem;">
              Запомнить меня
            </label>
          </div>

          <Button label="Войти" type="submit" class="submit-btn" />

          <div style="text-align: center; margin-top: 1rem">
            <router-link to="/sign-up">Нет аккаунта нашего банка?</router-link>
          </div>
        </Form>
      </template>
    </Card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// URL вашего backend API
const API_URL = 'http://localhost:8080/api/auth/login';

const login = ref('');
const password = ref('');
const rememberMe = ref(false);

const router = useRouter();

async function handleSubmit(event) {

  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        userIdLogin: login.value,
        userIdPassword: password.value,
      }),
    });

    const data = await response.json();

    if (!response.ok) {
      throw new Error(data.message || `Ошибка при входе (status ${response.status})`);
    }

    // Для примера: backend возвращает UserPersonalDto с полями:
    // { token, role, fullName, userIdLogin, ... }

    const { token, userIdRole, fullName, userIdLogin } = data;

    if (!token || !userIdRole) {
      throw new Error('Некорректный ответ от сервера');
    }

    if (rememberMe.value) {
      localStorage.setItem('token', token);
      localStorage.setItem('role', userIdRole);
      localStorage.setItem('name', fullName || '');
      localStorage.setItem('login', userIdLogin || '');
    } else {
      sessionStorage.setItem('token', token);
      sessionStorage.setItem('role', userIdRole);
      sessionStorage.setItem('name', fullName || '');
      sessionStorage.setItem('login', userIdLogin || '');
    }

    // Перенаправляем пользователя в зависимости от роли
    if (userIdRole === 'CLIENT') {
      router.push('/apply_form');
    } else if (userIdRole === 'ADMIN') {
      router.push('/applications');
    } else {
      alert('Неизвестная роль: доступ ограничен');
    }
  } catch (error) {
    console.error('Ошибка при входе:', error);
    alert('Ошибка при входе: ' + error.message);
  }
}
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Arial", sans-serif;
  flex-direction: column;
}

.auth-form {
  padding: 1.5rem;
  min-width: 500px;
  text-align: center;
}

.form-group {
  margin-bottom: 1.5rem;
  text-align: left;
}

.input-field {
  margin-top: 1.5rem;
  width: 100%;
  display: block;
}

.submit-btn {
  margin-top: 1.5rem;
  width: 100%;
}
</style>