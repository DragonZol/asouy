<template>
  <div class="auth-container">
    <h2 class="auth-header">
      <i class="pi pi-globe" style="color: green; font-size: 3rem"></i> ВЛБ -
      Ваш лучший банк
    </h2>
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
            <!-- FIXME Пароль не растягивается по ширине-->
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

          <div style="display: flex">
            <Checkbox v-model="remember_me" binary />
            <label for="remember_me" style="margin-left: 0.5rem"
              >Запомнить меня</label
            >
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

<script>
import { ref } from "vue";

const remember_me = ref(false);

export default {
  data() {
    return {
      login: "",
      password: "",
      remember_me: false,
    };
  },
  methods: {
    handleSubmit() {
      console.log("Вход:", this.login, this.password, this.remember_me);
    },
  },
};
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: "Arial", sans-serif;
  flex-direction: column;
}

.auth-header {
  font-size: 2.5rem;
  margin-bottom: 2rem;
  text-align: center;
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
