<template>
    <Card class="list-container">
      <template #content>
        <h2 class="section-title">Список заявок</h2>
        <div
          v-for="(application, index) in applications"
          :key="application.id"
          class="application-item"
        >
          <div>
            <strong>Заявка №{{ application.id }}</strong>
            <p>Сумма: {{ application.amount }} руб.</p>
            <p>Срок: {{ application.term_years }} лет</p>
            <p>Статус: {{ application.status }}</p>
            <p>Дата создания: {{ application.created_at }}</p>
          </div>
          <Button label="Открыть" class="view-btn" @click="viewApplication(application.id)" />
        </div>
      </template>
    </Card>
  </template>
  
  <script>
  import { ref, onMounted } from "vue";
  import { useRouter } from "vue-router";
  
  export default {
    name: "ApplicationList",
    setup() {
      const router = useRouter();
      const applications = ref([]);
  
      onMounted(async () => {
        try {
          // Получаем user_id и role из localStorage или sessionStorage
          const userId = localStorage.getItem("id") || sessionStorage.getItem("id") || 0;
          const role = localStorage.getItem("role") || sessionStorage.getItem("role") || "client";
          const url = `http://185.255.179.139/my_api/index.php?route=loan-applications&user_id=${userId}&role=${role}`;
  
          const response = await fetch(url, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
          });
          if (!response.ok) {
            throw new Error("Ошибка получения заявок (status " + response.status + ")");
          }
          const data = await response.json();
          applications.value = data;
        } catch (error) {
          console.error("Ошибка получения заявок:", error);
          alert("Ошибка получения заявок: " + error.message);
        }
      });
  
      const viewApplication = (id) => {
        router.push({ name: "ApplicationDetails", params: { id } });
      };
  
      return { applications, viewApplication };
    },
  };
  </script>
  
  <style scoped>
  .list-container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
  }
  
  .section-title {
    margin-top: 20px;
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
  }
  
  .application-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0;
    border-bottom: 1px solid #ddd;
  }
  
  .view-btn {
    margin-left: 20px;
  }
  </style>
  