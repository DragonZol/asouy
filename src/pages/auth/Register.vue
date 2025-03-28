<template>
  <div class="auth-container">
    <Card class="auth-form">
      <template #title>Регистрация</template>
      <template #content>
        <Form @submit="handleSubmit">
          <!-- Телефон -->
          <div class="form-group">
            <label for="userIdPhoneNumber">Номер телефона</label>
            <InputMask
              v-model="userIdPhoneNumber"
              id="userIdPhoneNumber"
              mask="+9(999) 999-99-99"
              placeholder="Введите номер телефона"
              required
              class="input-field"
            />
          </div>

          <!-- Логин -->
          <div class="form-group">
            <label for="userIdLogin">Логин</label>
            <InputText
              v-model="userIdLogin"
              id="userIdLogin"
              placeholder="Введите имя пользователя"
              required
              class="input-field"
            />
          </div>

          <!-- Пароль -->
          <div class="form-group">
            <label for="userIdPassword">Пароль</label>
            <Password
              v-model="userIdPassword"
              id="userIdPassword"
              placeholder="Введите ваш пароль"
              toggleMask
              required
              class="input-field"
              style="width: 100%"
              promptLabel="Выберите пароль"
              weakLabel="Слишком простой"
              mediumLabel="Средний"
              strongLabel="Надёжный"
            />
          </div>

          <!-- Email -->
          <div class="form-group">
            <label for="userIdEmail">Электронная почта</label>
            <InputText
              v-model="userIdEmail"
              id="userIdEmail"
              placeholder="Введите email"
              type="email"
              required
              class="input-field"
            />
          </div>

          <!-- ФИО -->
          <div class="form-group">
            <label for="fullName">ФИО</label>
            <InputText
              v-model="fullName"
              id="fullName"
              placeholder="Фамилия Имя Отчество"
              required
              class="input-field"
            />
          </div>

          <!-- Дата рождения -->
          <div class="form-group">
            <label for="birthDate">Дата рождения</label>
            <DatePicker
              v-model="birthDate"
              id="birthDate"
              dateFormat="yy-mm-dd"
              showIcon
              placeholder="ГГГГ-ММ-ДД"
              required
              class="input-field"
            />
          </div>

          <!-- Регион -->
          <div class="form-group">
            <label for="region">Регион</label>
            <InputText
              v-model="region"
              id="region"
              placeholder="Например, Москва"
              required
              class="input-field"
            />
          </div>

          <!-- Кнопка регистрации -->
          <Button label="Зарегистрироваться" type="submit" class="submit-btn" />

          <!-- Ссылка на вход -->
          <div style="text-align: center; margin-top: 1rem">
            <router-link to="/sign-in">Уже есть аккаунт? Войти</router-link>
          </div>
        </Form>
      </template>
    </Card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// API базовый URL (замени на свой backend адрес)
const API_URL = 'http://localhost:8080/api/auth/register';

const router = useRouter();

const userIdPhoneNumber = ref('');
const userIdLogin = ref('');
const userIdPassword = ref('');
const userIdEmail = ref('');
const fullName = ref('');
const birthDate = ref(null);
const region = ref('');

// Регистрация
async function handleSubmit(event) {
  event.preventDefault();

  try {
    const payload = {
      userIdPhoneNumber: userIdPhoneNumber.value.replace(/[^0-9+]/g, ''),
      userIdLogin: userIdLogin.value,
      userIdPasswordHash: userIdPassword.value,
      userIdRole: 'CLIENT',
      fullName: fullName.value,
      birthDate: birthDate.value
        ? new Date(birthDate.value).toISOString().split('T')[0]
        : null,
      email: userIdEmail.value,
      region: region.value,
    };

    const response = await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });

    const result = await response.json();

    if (!response.ok) {
      throw new Error(result.message || `Ошибка регистрации (status ${response.status})`);
    }

    alert('Регистрация прошла успешно!');
    router.push('/sign-in');
  } catch (error) {
    console.error('Ошибка при регистрации:', error);
    alert('Ошибка при регистрации: ' + error.message);
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
  padding: 1rem;
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

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.input-field {
  margin-top: 0.5rem;
  width: 100%;
  display: block;
}

.submit-btn {
  margin-top: 1.5rem;
  width: 100%;
}
</style>