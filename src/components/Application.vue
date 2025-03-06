<template>
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
                    <DatePicker v-model="birthDate" id="birthDate" dateFormat="dd/mm/yy" showIcon class="input-field" />
                </div>
                <div>
                    <label for="phone">Мобильный телефон</label>
                    <InputMask v-model="phone" id="phone" mask="8 (999) 999-99-99" class="input-field" />
                </div>
                <div>
                    <label for="email">Электронная почта</label>
                    <InputText v-model="email" type="email" id="email" class="input-field" />
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
                    <InputNumber v-model="creditAmount" id="creditAmount" class="input-field" suffix=" руб." />
                </div>
                <div>
                    <label for="creditTerm">Срок кредита</label>
                    <Select v-model="creditTerm" :options="terms" optionLabel="label" placeholder="Выберите срок кредита" class="input-field" />
                </div>
                <div>
                    <label for="creditCity">Город получения кредита</label>
                    <Select v-model="creditCity" :options="cities" optionLabel="name" placeholder="Выберите город" class="input-field" />
                </div>
            </div>

            <h2 class="section-title">Документы</h2>
            <div class="form-grid">
                <div>
                    <label for="passport">Серия и номер паспорта</label>
                    <InputMask v-model="passport" id="passport" mask="99 99 999999" class="input-field" />
                </div>
                <div>
                    <label for="issueDate">Дата выдачи</label>
                    <DatePicker v-model="issueDate" id="issueDate" dateFormat="dd.mm.yy" showIcon
                        class="input-field" />
                </div>
                <div>
                    <label for="income">Доход по месту работы</label>
                    <InputNumber v-model="income" id="income" class="input-field" suffix=" руб." />
                </div>
                <div>
                    <label for="workExperience">Трудовой стаж</label>
                    <InputText v-model="workExperience" id="workExperience" class="input-field" />
                </div>
            </div>
            <Button label="Отправить заявку" class="submit-btn" />
        </template>
    </Card>
</template>

<script>
import { ref } from "vue";

export default {
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
    watch: {
        creditTerm(val) {
            console.log("creditTerm updated:", val);
        },
        creditCity(val) {
            console.log("creditCity updated:", val);
        },
    },
};
</script>

<style scoped>
.form-container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
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
