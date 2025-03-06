<template>
  <div class="auth-container">
    <Card class="auth-form">
      <template #title>Регистрация</template>
      <template #content>
        <Form @submit="handleSubmit">
          <div class="form-group">
            <InputMask
              v-model="phone_number"
              id="phone_number"
              mask="+9(999) 999-99-99"
              placeholder="Введите номер телефона"
              required
              class="input-field"
            />
          </div>

          <div class="form-group">
            <InputText
              v-model="username"
              id="username"
              placeholder="Введите имя пользователя"
              required
              class="input-field"
            />
          </div>
          <!-- FIXME Пароль не растягивается по ширине-->
          <div class="form-group">
            <Password
              v-model="password"
              id="password"
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

          <Button label="Зарегистрироваться" type="submit" class="submit-btn" />

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

export default {
  data() {
    return {
      username: "",
      email: "",
      password: "",
    };
  },
  methods: {
    handleSubmit() {
      console.log("Регистрация:", this.username, this.phone_number, this.password);
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
}

.form-group {
  margin-bottom: 1.5rem;
  text-align: left;
}

.submit-btn {
  margin-top: 1.5rem;
  width: 100%;
}

.input-field {
  margin-top: 1.5rem;
  width: 100%;
  display: block;
}

.auth-form {
  padding: 1.5rem;
  min-width: 500px;
  text-align: center;
}
</style>
