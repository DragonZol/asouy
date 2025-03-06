<script setup>
function toggleDarkMode() {
    const isDarkMode = document.documentElement.classList.toggle('my-app-dark');
    localStorage.setItem('darkMode', isDarkMode ? 'enabled' : 'disabled');
}

// Проверяем состояние темной темы при запуске
if (localStorage.getItem('darkMode') === 'enabled') {
    document.documentElement.classList.add('my-app-dark');
} 
</script>

<template>
  <Button label="Toggle Dark Mode" @click="toggleDarkMode()" />
<router-view></router-view>
</template>

<style scoped>

</style>
