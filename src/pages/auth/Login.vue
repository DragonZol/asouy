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
            <Checkbox v-model="remember_me" binary />
            <label for="remember_me" style="margin-left: 0.5rem;">
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

<script>
export default {
  name: "Login",
  data() {
    return {
      login: "",
      password: "",
      remember_me: false,
    };
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await fetch("http://185.255.179.139/my_api/index.php?route=auth/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            login: this.login,
            password: this.password,
          }),
        });

        const data = await response.json();
        if (!response.ok) {
          throw new Error(data.error || "Ошибка при входе (status " + response.status + ")");
        }

        // Сохраняем токен в localStorage или sessionStorage
        if (this.remember_me) {
          localStorage.setItem("token", data.token)
          localStorage.setItem("role", data.role)
          localStorage.setItem("name", data.name)
        } else {
          sessionStorage.setItem("token", data.token)
          sessionStorage.setItem("role", data.role)
          sessionStorage.setItem("name", data.name)
        }

        // Перенаправляем пользователя в зависимости от роли
        if (data.role === "client") {
          this.$router.push("/apply_form");
        } else if (data.role === "admin") {
          this.$router.push("/applications");
        } else {
          alert("Неизвестная роль, перенаправление невозможно");
        }

        console.log("Успешный вход:", data);
      } catch (error) {
        console.error("Ошибка при входе:", error);
        alert("Ошибка при входе: " + error.message);
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
