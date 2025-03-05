<template>
  <div class="notfound-container">
    <Card>
      <template #title>Страница не найдена!</template>
      <template #content>
        <i class="pi pi-exclamation-triangle notfound-icon"></i>
        <p>Извините, но запрашиваемая страница не существует.</p>
        <router-link to="/">
          <Button>Вернуться на главную</Button>
        </router-link>
      </template>
    </Card>
  </div>
</template>

<script>

</script>

<style scoped>
.notfound-container {
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  height: 100vh;
  font-family: 'Arial', sans-serif;
}

.notfound-content {
  padding: 2rem;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.notfound-icon {
  font-size: 5rem;
  color: #f44336;
  margin-top: 1rem;
  margin-bottom: 1rem;
}

p {
  font-size: 1.1rem;
  color: #777;
  margin-bottom: 1.5rem;
}
</style>
