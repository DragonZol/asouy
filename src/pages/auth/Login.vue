<template>
    <div class="auth-container">
      <h2>Вход</h2>
      <form @submit.prevent="handleSubmit">
        <div>
          <label for="email">Электронная почта</label>
          <input type="email" v-model="email" id="email" required />
        </div>
        <div>
          <label for="password">Пароль</label>
          <input type="password" v-model="password" id="password" required />
        </div>
        <button type="submit">Войти</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: '',
        password: '',
      };
    },
    methods: {
      handleSubmit() {
        // Здесь будет логика отправки данных на сервер или в хранилище
        console.log('Вход:', this.email, this.password);
      },
    },
  };
  </script>
  
  <style scoped>
  .auth-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
  }
  
  .auth-container form {
    display: flex;
    flex-direction: column;
  }
  
  .auth-container div {
    margin-bottom: 10px;
  }
  
  .auth-container button {
    background-color: #4CAF50;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .auth-container button:hover {
    background-color: #45a049;
  }
  </style>
  