<template>
  <div class="header-container">
    <!-- Левая часть: логотип и название -->
    <h2 class="header">
      <i class="pi pi-globe" style="color: green; font-size: 3rem"></i> ВЛБ - Ваш лучший банк
    </h2>

    <!-- Правая часть: всегда показываем переключатель темы, а информацию о пользователе – только если не страница входа/регистрации -->
    <div class="header-right-section">
      <!-- Переключатель темы -->
      <div class="toggle-container">
        <div class="toggle-text">
          <i
            :class="[
              'pi',
              { 'pi-sun': !checked, 'pi-moon': checked },
              'toggle-icon'
            ]"
          />
        </div>
        <ToggleSwitch v-model="checked" class="custom-toggle">
          <template #handle="{ checked }"></template>
        </ToggleSwitch>
      </div>

      <!-- Блок информации о пользователе и кнопка выхода показывается только когда мы не на страницах входа/регистрации -->
      <template v-if="!isAuthPage">
        <div class="user-info">
          <i class="pi pi-user user-icon"></i>
          <span class="user-name">{{ userFullName }}</span>
          <button class="logout-btn" @click="logout">
            <i class="pi pi-sign-out"></i>
            Выход
          </button>
        </div>
      </template>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import ToggleSwitch from 'primevue/toggleswitch'

const router = useRouter()
const route = useRoute()

// Определяем, является ли текущий маршрут страницей входа или регистрации
const isAuthPage = computed(() => {
  return route.path === '/sign-in' || route.path === '/sign-up'
})

// Считываем состояние тёмной темы из localStorage
const checked = ref(localStorage.getItem('darkMode') === 'enabled')

// Считываем ФИО пользователя из sessionStorage или задаём заглушку, если данных нет
const userFullName = ref(sessionStorage.getItem('name') || 'Пользователь С.Л.')

// Функция переключения тёмной темы
function toggleDarkMode() {
  const isDarkMode = checked.value
  document.documentElement.classList.toggle('my-app-dark', isDarkMode)
  localStorage.setItem('darkMode', isDarkMode ? 'enabled' : 'disabled')
}

// Следим за изменением переключателя темы
watch(checked, toggleDarkMode)

// Если при загрузке странички значение в localStorage === 'enabled', добавляем класс для тёмной темы
if (localStorage.getItem('darkMode') === 'enabled') {
  document.documentElement.classList.add('my-app-dark')
}

// Функция для выхода из профиля
function logout() {
  // Удаляем данные пользователя из sessionStorage
  sessionStorage.removeItem('token')
  sessionStorage.removeItem('role')
  sessionStorage.removeItem('name')
  
  // Удаляем данные пользователя из localStorage (на случай, если используется "Запомнить меня")
  localStorage.removeItem('token')
  localStorage.removeItem('role')
  localStorage.removeItem('name')

  // Переадресация на страницу логина
  router.push('/sign-in')
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

/* Правая часть (переключатель + пользователь) */
.header-right-section {
  display: flex;
  align-items: center;
  gap: 20px;
}

/* Стили переключателя темы */
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

/* Стили PrimeVue ToggleSwitch */
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

/* Секция пользователя */
.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.user-icon {
  font-size: 1.8rem;
  color: #555;
}

.user-name {
  font-weight: 500;
  font-size: 1.1rem;
}

/* Стили кнопки выхода */
.logout-btn {
  background-color: transparent;
  border: none;
  color: #555;
  font-size: 1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 5px;
  transition: color 0.3s;
}

.logout-btn:hover {
  color: #d9534f;
}
</style>