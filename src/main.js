import { createApp } from 'vue'

// Import PrimeVue stuff
import PrimeVue from 'primevue/config';
import { definePreset } from '@primeuix/themes';
import Aura  from '@primeuix/themes/aura';
import Button from "primevue/button"
import Card from 'primevue/card';
import Checkbox from 'primevue/checkbox';
import InputText from 'primevue/inputtext';

import Password from 'primevue/password';

import { Form } from '@primevue/forms';
import 'primeicons/primeicons.css'

import './style.css'
import App from './App.vue'
import router from './router';
  


const MyPreset = definePreset(Aura, {
    semantic: {
        colorScheme: {
            light: {
                surface: {
                    0: '#ffffff',
                    50: '{zinc.50}',
                    100: '{zinc.100}',
                    200: '{zinc.200}',
                    300: '{zinc.300}',
                    400: '{zinc.400}',
                    500: '{zinc.500}',
                    600: '{zinc.600}',
                    700: '{zinc.700}',
                    800: '{zinc.800}',
                    900: '{zinc.900}',
                    950: '{zinc.950}'
                }
            },
            dark: {
                surface: {
                    0: '#ffffff',
                    50: '{slate.50}',
                    100: '{slate.100}',
                    200: '{slate.200}',
                    300: '{slate.300}',
                    400: '{slate.400}',
                    500: '{slate.500}',
                    600: '{slate.600}',
                    700: '{slate.700}',
                    800: '{slate.800}',
                    900: '{slate.900}',
                    950: '{slate.950}'
                }
            }
        }
    }
});


const app = createApp(App);
app.component('Button', Button);
app.component('Card', Card);
app.component('Checkbox', Checkbox);
app.component('InputText', InputText);
app.component('Form', Form);
app.component('Password', Password);
app.use(PrimeVue, {
    theme: {
        preset: MyPreset,
        options: {
            darkModeSelector: '.my-app-dark'
        },
    }
});
app.use(router)
app.mount('#app')