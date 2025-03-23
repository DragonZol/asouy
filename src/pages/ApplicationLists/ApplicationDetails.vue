<template>
    <Card class="details-container">
        <template #content>
            <h2 class="section-title">Детали заявки</h2>
            <div class="details-grid">
                <div><strong>ФИО:</strong> {{ application.fullName }}</div>
                <div><strong>Дата рождения:</strong> {{ application.birthDate }}</div>
                <div><strong>Телефон:</strong> {{ application.phone }}</div>
                <div><strong>Email:</strong> {{ application.email }}</div>
                <div><strong>Регион регистрации:</strong> {{ application.region }}</div>
                <div><strong>Гражданство РФ:</strong> {{ application.isCitizen ? 'Да' : 'Нет' }}</div>
                <div><strong>Согласие на обработку данных:</strong> {{ application.isAgree ? 'Да' : 'Нет' }}</div>
                <div><strong>Размер кредита:</strong> {{ application.creditAmount }} руб.</div>
                <div><strong>Срок кредита:</strong> {{ application.creditTerm }} мес.</div>
                <div><strong>Город получения кредита:</strong> {{ application.creditCity }}</div>
                <div><strong>Серия и номер паспорта:</strong> {{ application.passport }}</div>
                <div><strong>Дата выдачи паспорта:</strong> {{ application.issueDate }}</div>
                <div><strong>Доход по месту работы:</strong> {{ application.income }} руб.</div>
                <div><strong>Трудовой стаж:</strong> {{ application.workExperience }}</div>
            </div>
            <div class="actions">
                <Button label="Одобрить" class="approve-btn" @click="approveApplication" />
                <Button label="Отклонить" class="reject-btn" @click="rejectApplication" />
            </div>
        </template>
    </Card>
</template>

<script>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";

export default {
    setup() {
        const route = useRoute();
        const router = useRouter();
        const id = route.params.id;

        // Заглушка: данные заявок
        const applications = [
            {
                fullName: "Иван Иванов",
                birthDate: "01.01.1990",
                phone: "8 (999) 123-45-67",
                email: "ivan@example.com",
                region: "Москва",
                isCitizen: true,
                isAgree: true,
                creditAmount: 200000,
                creditTerm: 24,
                creditCity: "Москва",
                passport: "45 12 345678",
                issueDate: "01.01.2015",
                income: 50000,
                workExperience: "5 лет"
            },
            {
                fullName: "Мария Петрова",
                birthDate: "15.05.1985",
                phone: "8 (922) 456-78-90",
                email: "maria@example.com",
                region: "Санкт-Петербург",
                isCitizen: true,
                isAgree: true,
                creditAmount: 150000,
                creditTerm: 36,
                creditCity: "Санкт-Петербург",
                passport: "12 34 567890",
                issueDate: "10.06.2016",
                income: 65000,
                workExperience: "10 лет"
            }
        ];

        const application = ref(applications[id]);

        const approveApplication = () => {
            console.log("✅ Заявка одобрена:", application.value);
            router.push({ name: "ApplicationList" });
        };

        const rejectApplication = () => {
            console.log("❌ Заявка отклонена:", application.value);
            router.push({ name: "ApplicationList" });
        };

        return { application, approveApplication, rejectApplication };
    }
};
</script>

<style scoped>
.details-container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
    background-color: var(--light-bg, #fff);
    color: var(--light-text, #333);
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Темная тема */
.my-app-dark .details-container {
    background-color: var(--dark-bg, #1e1e1e);
    color: var(--dark-text, #e0e0e0);
    border: 1px solid #444;
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
