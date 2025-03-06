import { createApp } from 'vue'

// Import PrimeVue stuff
import PrimeVue from 'primevue/config';
import Aura  from '@primeuix/themes/aura';
import ToggleSwitch from 'primevue/toggleswitch';
import Button from "primevue/button"
import Card from 'primevue/card';
import Checkbox from 'primevue/checkbox';
import InputText from 'primevue/inputtext';
import InputNumber from 'primevue/inputnumber';
import InputMask from 'primevue/inputmask';
import Password from 'primevue/password';
import DatePicker from 'primevue/datepicker';
import Select from 'primevue/select';

import { Form } from '@primevue/forms';
import 'primeicons/primeicons.css'

import './style.css'
import App from './App.vue'
import router from './router';
  


const app = createApp(App);
app.component('ToggleSwitch', ToggleSwitch);
app.component('Button', Button);
app.component('Card', Card);
app.component('Checkbox', Checkbox);
app.component('InputText', InputText);
app.component('Form', Form);
app.component('Password', Password);
app.component('InputMask', InputMask);
app.component('DatePicker', DatePicker);
app.component('InputNumber', InputNumber);
app.component('Select', Select);
app.use(PrimeVue, {
    theme: {
        preset: Aura,
        options: {
            darkModeSelector: '.my-app-dark'
        },
    }
});
app.use(router)
app.mount('#app')