<template>
    <div class="header-container">
      <h2 class="header">
        <i class="pi pi-globe" style="color: green; font-size: 3rem"></i> ВЛБ -
        Ваш лучший банк
      </h2>
      <div class="toggle-container">
        <div class="toggle-text">
          <i :class="['pi', { 'pi-sun': !checked, 'pi-moon': checked }]" class="toggle-icon" />
        </div>
        <ToggleSwitch v-model="checked" class="custom-toggle">
          <template #handle="{ checked }"></template>
        </ToggleSwitch>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, watch } from 'vue';
  
  const checked = ref(localStorage.getItem('darkMode') === 'enabled');
  
  function toggleDarkMode() {
    const isDarkMode = checked.value;
    document.documentElement.classList.toggle('my-app-dark', isDarkMode);
    localStorage.setItem('darkMode', isDarkMode ? 'enabled' : 'disabled');
  }
  
  watch(checked, toggleDarkMode);
  
  if (localStorage.getItem('darkMode') === 'enabled') {
    document.documentElement.classList.add('my-app-dark');
  }
  </script>
  
  <style scoped>
  .header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
  }
  
  .header {
    font-size: 2.5rem;
    text-align: left;
  }
  
  .toggle-container {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  .toggle-text {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  .toggle-icon {
    font-size: 1.5rem;
    transition: all 0.3s;
  }
  
  .pi-sun {
    color: rgb(255, 183, 0);
  }
  
  .pi-moon {
    color: lightblue;
  }
  
  .p-toggleswitch {
    --switch-height: 50px;
    --switch-width: 100px;
  }
  
  .p-toggleswitch-slider {
    border-radius: 25px;
    height: 30px;
    width: 60px;
  }
  
  .p-toggleswitch-handle {
    width: 36px;
    height: 36px;
    border-radius: 50%;
  }
  </style>
  