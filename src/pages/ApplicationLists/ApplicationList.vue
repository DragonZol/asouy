<template>
    <Card class="list-container">
        <template #content>
            <h2 class="section-title">Список заявок</h2>
            <div v-for="(application, index) in applications" :key="index" class="application-item">
                <div>
                    <strong>{{ application.fullName }}</strong>
                    <p>Email: {{ application.email }}</p>
                    <p>Кредит: {{ application.creditAmount }} руб., Город: {{ application.creditCity }}</p>
                </div>
                <Button label="Открыть" class="view-btn" @click="viewApplication(index)" />
            </div>
        </template>
    </Card>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
    setup() {
        const router = useRouter();

        // Заглушка: список заявок
        const applications = ref([
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
        ]);

        const viewApplication = (index) => {
            router.push({ name: "ApplicationDetails", params: { id: index } });
        };

        return { applications, viewApplication };
    }
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
