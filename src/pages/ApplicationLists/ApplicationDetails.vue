<template>
    <Card class="details-container">
      <template #content>
        <!-- Кнопка "Назад" -->
        <div class="back-btn-container">
          <Button
            icon="pi pi-arrow-left"
            class="p-button-text"
            label="Назад"
            @click="goBack"
          />
        </div>
  
        <!-- Основной контент: две колонки -->
        <div class="details-wrapper" v-if="application">
          <!-- Левая колонка: детали заявки -->
          <div class="left-section">
            <h2 class="section-title">Детали заявки</h2>
            <div class="details-grid">
              <div><strong>ФИО:</strong> {{ application.full_name }}</div>
              <div><strong>Дата рождения:</strong> {{ application.birth_date }}</div>
              <div><strong>Телефон:</strong> {{ application.phone }}</div>
              <div><strong>Email:</strong> {{ application.email }}</div>
              <div><strong>Регион регистрации:</strong> {{ application.region }}</div>
              <div><strong>Гражданство РФ:</strong> {{ application.isCitizen ? 'Да' : 'Нет' }}</div>
              <div><strong>Согласие на обработку данных:</strong> {{ application.isAgree ? 'Да' : 'Нет' }}</div>
              <div><strong>Размер кредита:</strong> {{ application.amount }} руб.</div>
              <div><strong>Срок кредита:</strong> {{ application.term_years }} лет</div>
              <div><strong>Город получения кредита:</strong> {{ application.creditCity || application.city }}</div>
              <div>
                <strong>Паспорт:</strong>
                {{ application.passport_series }} {{ application.passport_number }}
              </div>
              <div><strong>Дата выдачи паспорта:</strong> {{ application.issue_date }}</div>
              <div><strong>Доход по месту работы:</strong> {{ application.income }} руб.</div>
              <div><strong>Трудовой стаж:</strong> {{ application.work_experience }}</div>
            </div>
            <div class="actions">
              <Button label="Одобрить" class="approve-btn" @click="approveApplication" />
              <Button label="Отклонить" class="reject-btn" @click="rejectApplication" />
            </div>
          </div>
  
          <!-- Правая колонка: чат поддержки -->
          <div class="right-section">
            <SupportChat />
          </div>
        </div>
        <div v-else>
          Загрузка данных...
        </div>
      </template>
    </Card>
  </template>
  
  <script>
  import { ref, onMounted } from "vue";
  import { useRoute, useRouter } from "vue-router";
  import SupportChat from "../SupportChat.vue";
  
  export default {
    name: "ApplicationDetails",
    components: { SupportChat },
    setup() {
      const route = useRoute();
      const router = useRouter();
      const id = route.params.id;
      const application = ref(null);
  
      // Функция загрузки данных заявки через API
      const fetchApplication = async () => {
        try {
          const response = await fetch(`http://185.255.179.139/my_api/index.php?route=loan-applications/${id}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
          });
          if (!response.ok) {
            throw new Error("Ошибка получения заявки (status " + response.status + ")");
          }
          const data = await response.json();
          application.value = data;
        } catch (error) {
          console.error("Ошибка получения заявки:", error);
          alert("Ошибка получения заявки: " + error.message);
        }
      };
  
      onMounted(() => {
        fetchApplication();
      });
  
      // Одобрение заявки через API
      const approveApplication = async () => {
        try {
          const response = await fetch(`http://185.255.179.139/my_api/index.php?route=loan-applications/${id}/approve`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
          });
          if (!response.ok) {
            throw new Error("Ошибка одобрения заявки (status " + response.status + ")");
          }
          const data = await response.json();
          console.log("Заявка одобрена:", data);
          router.push({ name: "ApplicationList" });
        } catch (error) {
          console.error("Ошибка одобрения заявки:", error);
          alert("Ошибка одобрения заявки: " + error.message);
        }
      };
  
      // Отклонение заявки через API
      const rejectApplication = async () => {
        try {
          const response = await fetch(`http://185.255.179.139/my_api/index.php?route=loan-applications/${id}/reject`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
          });
          if (!response.ok) {
            throw new Error("Ошибка отклонения заявки (status " + response.status + ")");
          }
          const data = await response.json();
          console.log("Заявка отклонена:", data);
          router.push({ name: "ApplicationList" });
        } catch (error) {
          console.error("Ошибка отклонения заявки:", error);
          alert("Ошибка отклонения заявки: " + error.message);
        }
      };
  
      const goBack = () => {
        router.back();
      };
  
      return {
        application,
        approveApplication,
        rejectApplication,
        goBack,
      };
    },
  };
  </script>
  
  <style scoped>
  .details-container {
    max-width: 1600px;
    margin: auto;
    padding: 20px;
    background-color: var(--light-bg, #fff);
    color: var(--light-text, #333);
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  }
  
  /* Тёмная тема */
  .my-app-dark .details-container {
    background-color: var(--dark-bg, #1e1e1e);
    color: var(--dark-text, #e0e0e0);
    border: 1px solid #444;
  }
  
  /* Тёмная тема для элементов details-grid */
  .my-app-dark .details-grid div {
    background-color: var(--dark-item-bg, #2e2e2e);
    color: var(--dark-text, #e0e0e0);
    border: 1px solid #555;
  }
  
  /* Контейнер для кнопки "Назад" */
  .back-btn-container {
    margin-bottom: 10px;
  }
  
  /* Обёртка для двух колонок */
  .details-wrapper {
    display: flex;
    gap: 20px;
    align-items: stretch;
  }
  
  /* Левая колонка */
  .left-section {
    flex: 2;
  }
  
  /* Правая колонка */
  .right-section {
    flex: 1;
    display: flex;
    flex-direction: column;
  }
  
  .section-title {
    margin-top: 20px;
    border-bottom: 2px solid #ddd;
    padding-bottom: 10px;
    font-size: 1.5rem;
  }
  
  .details-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 15px;
    margin: 20px 0;
  }
  
  .details-grid div {
    background-color: #f9f9f9;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #eee;
  }
  
  .actions {
    display: flex;
    justify-content: space-between;
    gap: 10px;
  }
  
  .approve-btn {
    background-color: #4caf50;
    color: white;
    width: 48%;
    padding: 10px;
    border-radius: 5px;
  }
  
  .reject-btn {
    background-color: #f44336;
    color: white;
    width: 48%;
    padding: 10px;
    border-radius: 5px;
  }
  
  /* Темные стили кнопок */
  .my-app-dark .approve-btn {
    background-color: #66bb6a;
  }
  
  .my-app-dark .reject-btn {
    background-color: #e57373;
  }
  </style>
  