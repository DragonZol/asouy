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
              weakLabel="Слишком лёгкий"
              mediumLabel="Хороший пароль"
              strongLabel="Надёжный пароль"
            />
          </div>

          <!-- Email -->
          <div class="form-group">
            <label for="userIdEmail">Электронная почта</label>
            <InputText
              v-model="userIdEmail"
              id="userIdEmail"
              placeholder="Введите ваш email"
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
              placeholder="Введите полное имя (пример: Иванов Иван Иванович)"
              required
              class="input-field"
            />
          </div>

          <!-- Дата рождения -->
          <div class="form-group">
            <label for="birthDate">Дата рождения</label>
            <!-- Можно использовать DatePicker PrimeVue, если он подключён;
                 или обычный InputText, если нужно просто передать строку. -->
            <DatePicker
              v-model="birthDate"
              id="birthDate"
              dateFormat="yy-mm-dd"
              showIcon
              placeholder="ГГГГ-ММ-ДД"
              class="input-field"
            />
          </div>

          <!-- Регион -->
          <div class="form-group">
            <label for="region">Регион</label>
            <InputText
              v-model="region"
              id="region"
              placeholder="Например: Moscow"
              required
              class="input-field"
            />
          </div>

          <!-- Кнопка регистрации -->
          <Button label="Зарегистрироваться" type="submit" class="submit-btn" />

          <!-- Ссылка на вход -->
          <div style="text-align: center; margin-top: 1rem">
            <router-link to="/sign-in">Уже есть аккаунт? Войдите</router-link>
          </div>
        </Form>
      </template>
    </Card>
  </div>
</template>

<script>
import { ref } from "vue";
// Если вы используете axios, раскомментируйте строку ниже и закомментируйте пример с fetch.
// import axios from 'axios';

export default {
  name: "Register",
  data() {
    return {
      userIdPhoneNumber: "",
      userIdLogin: "",
      userIdPassword: "",
      userIdEmail: "",
      fullName: "",
      birthDate: null, // храним дату как объект или строку
      region: "",
    };
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await fetch("http://185.255.179.139/my_api/index.php?route=auth/register", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            userIdPhoneNumber: this.userIdPhoneNumber.replace(/[^0-9+]/g, ''),
            userIdLogin: this.userIdLogin,
            userIdPassword: this.userIdPassword,
            userIdEmail: this.userIdEmail,
            fullName: this.fullName,
            birthDate: this.birthDate
              ? new Date(this.birthDate).toISOString().split("T")[0]
              : null,
            region: this.region,
          }),
        });

        const result = await response.json();
        if (!response.ok) {
          // Выбрасываем ошибку с текстом, полученным от сервера
          throw new Error(result.error || `Ошибка при регистрации (status ${response.status})`);
        }

        console.log("Успешная регистрация:", result);
        this.$router.push("/sign-in");
      } catch (error) {
        console.error("Ошибка при регистрации:", error);
        alert("Ошибка при регистрации: " + error.message);
      }
    },

  },
};
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

.form-group {
  margin-bottom: 1.5rem;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.submit-btn {
  margin-top: 1.5rem;
  width: 100%;
}

.input-field {
  margin-top: 0.5rem;
  width: 100%;
  display: block;
}

.auth-form {
  padding: 1.5rem;
  min-width: 500px;
  text-align: center;
}
</style>
