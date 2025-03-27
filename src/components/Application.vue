<template>
    <!-- Общий контейнер, где будут две колонки: слева форма, справа чат -->
    <div class="application-wrapper">
      <!-- Левая колонка с формой -->
      <div class="left-section">
        <Card class="form-container">
          <template #content>
            <h2 class="section-title">Личные данные</h2>
            <div class="form-grid">
              <div>
                <label for="fullName">ФИО</label>
                <InputText v-model="fullName" id="fullName" class="input-field" />
              </div>
              <div>
                <label for="birthDate">Дата рождения</label>
                <DatePicker
                  v-model="birthDate"
                  id="birthDate"
                  dateFormat="dd/mm/yy"
                  showIcon
                  class="input-field"
                />
              </div>
              <div>
                <label for="phone">Мобильный телефон</label>
                <InputMask
                  v-model="phone"
                  id="phone"
                  mask="8 (999) 999-99-99"
                  class="input-field"
                />
              </div>
              <div>
                <label for="email">Электронная почта</label>
                <InputText
                  v-model="email"
                  type="email"
                  id="email"
                  class="input-field"
                />
              </div>
              <div>
                <label for="region">Регион регистрации</label>
                <InputText v-model="region" id="region" class="input-field" />
              </div>
            </div>
  
            <div class="checkbox-group">
              <Checkbox v-model="isCitizen" inputId="citizen" binary />
              <label for="citizen">Я являюсь гражданином РФ</label>
            </div>
            <div class="checkbox-group">
              <Checkbox v-model="isAgree" inputId="agree" binary />
              <label for="agree">Я согласен с обработкой персональных данных</label>
            </div>
  
            <h2 class="section-title">Ваш кредит</h2>
            <div class="form-grid">
              <div>
                <label for="creditAmount">Размер кредита</label>
                <InputNumber
                  v-model="creditAmount"
                  id="creditAmount"
                  class="input-field"
                  suffix=" руб."
                />
              </div>
              <div>
                <label for="creditTerm">Срок кредита</label>
                <!-- Добавлен optionValue, чтобы v-model возвращал число -->
                <Select
                  v-model="creditTerm"
                  :options="terms"
                  optionLabel="label"
                  optionValue="value"
                  placeholder="Выберите срок кредита"
                  class="input-field"
                />
              </div>
              <div>
                <label for="creditCity">Город получения кредита</label>
                <Select
                  v-model="creditCity"
                  :options="cities"
                  optionLabel="name"
                  optionValue="code"
                  placeholder="Выберите город"
                  class="input-field"
                />
              </div>
            </div>
  
            <h2 class="section-title">Документы</h2>
            <div class="form-grid">
              <div>
                <label for="passport">Серия и номер паспорта</label>
                <InputMask
                  v-model="passport"
                  id="passport"
                  mask="99 99 999999"
                  class="input-field"
                />
              </div>
              <div>
                <label for="issueDate">Дата выдачи</label>
                <DatePicker
                  v-model="issueDate"
                  id="issueDate"
                  dateFormat="dd.mm.yy"
                  showIcon
                  class="input-field"
                />
              </div>
              <div>
                <label for="income">Доход по месту работы</label>
                <InputNumber
                  v-model="income"
                  id="income"
                  class="input-field"
                  suffix=" руб."
                />
              </div>
              <div>
                <label for="workExperience">Трудовой стаж</label>
                <InputText
                  v-model="workExperience"
                  id="workExperience"
                  class="input-field"
                />
              </div>
            </div>
            <Button label="Отправить заявку" class="submit-btn" @click="submitApplication" />
          </template>
        </Card>
      </div>
  
      <!-- Правая колонка с чатом поддержки -->
      <div class="right-section">
        <SupportChat />
      </div>
    </div>
  </template>
  
  <script>
  import SupportChat from "../pages/SupportChat.vue";
  
  export default {
    name: "Application",
    components: { SupportChat },
    data() {
      return {
        fullName: "",
        birthDate: null,
        phone: "",
        email: "",
        region: "",
        isCitizen: false,
        isAgree: false,
        creditAmount: null,
        creditTerm: null,
        creditCity: null,
        passport: "",
        issueDate: null,
        income: null,
        workExperience: "",
        cities: [
          { name: "Москва", code: "RU-MOW" },
          { name: "Санкт-Петербург", code: "RU-SPB" },
          { name: "Новосибирск", code: "RU-NVS" },
          { name: "Екатеринбург", code: "RU-EKT" },
          { name: "Нижний Новгород", code: "RU-NN" }
        ],
        terms: [
          { label: "12 месяцев", value: 12 },
          { label: "24 месяца", value: 24 },
          { label: "36 месяцев", value: 36 },
          { label: "48 месяцев", value: 48 }
        ]
      };
    },
    methods: {
      async submitApplication() {
        if (!this.creditAmount || !this.creditTerm) {
          alert("Пожалуйста, заполните поля кредита (сумму и срок).");
          return;
        }
  
        const payload = {
          user_id: 1, // Захардкожено для примера; используйте id авторизованного пользователя
          amount: this.creditAmount,
          term_years: this.creditTerm / 12, // переводим месяцы в годы
          // Для города используем выбранное значение (code)
          city: this.creditCity || null,
          monthly_income: this.income,
          work_experience_years: this.workExperience,
          // Если approver_id не обязателен, передаём null; иначе установите существующий id пользователя
          approver_id: null,
          credit_score: 700, // Условная заглушка
          comment: `
            ФИО: ${this.fullName}
            Дата рождения: ${this.birthDate}
            Паспорт: ${this.passport}
            Дата выдачи: ${this.issueDate}
            Телефон: ${this.phone}
            Email: ${this.email}
            Регион: ${this.region}
            Гражданин РФ: ${this.isCitizen}
            Согласие на обработку: ${this.isAgree}
          `
        };
  
        // Если у вас в localStorage или sessionStorage сохранён токен, добавляем его в заголовок
        const token = localStorage.getItem("token") || sessionStorage.getItem("token");
  
        try {
          const response = await fetch("http://185.255.179.139/my_api/index.php?route=loan-applications", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              ...(token ? { "Authorization": `Bearer ${token}` } : {})
            },
            body: JSON.stringify(payload)
          });
  
          if (!response.ok) {
            const errorText = await response.text();
            console.error("Response error text:", errorText);
            throw new Error(`Ошибка при отправке заявки: ${response.status} ${response.statusText}`);
          }
  
          const text = await response.text();
          let data = {};
          if (text) {
            try {
              data = JSON.parse(text);
            } catch (e) {
              console.error("Ошибка парсинга JSON:", e);
              throw new Error("Ошибка обработки ответа сервера");
            }
          } else {
            console.warn("Ответ сервера пуст");
          }
  
          console.log("Заявка отправлена:", data);
          alert("Заявка успешно отправлена!");
        } catch (error) {
          console.error("Ошибка при отправке заявки:", error);
          alert(error.message);
        }
      }
    },
    watch: {
      creditTerm(val) {
        console.log("creditTerm updated:", val);
      },
      creditCity(val) {
        console.log("creditCity updated:", val);
      }
    }
  };
  </script>
  
  <style scoped>
  .application-wrapper {
    display: flex;
    gap: 20px;
    align-items: stretch;
    max-width: 1200px;
    margin: 0 auto;
  }
  .left-section {
    flex: 2;
  }
  .right-section {
    flex: 1;
    display: flex;
    flex-direction: column;
  }
  .form-container {
    padding: 20px;
    flex: 1;
  }
  .section-title {
    margin-top: 20px;
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
  }
  .form-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 15px;
  }
  .input-field {
    width: 100%;
  }
  .checkbox-group {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-top: 10px;
  }
  .submit-btn {
    width: 100%;
    margin-top: 20px;
  }
  </style>
  