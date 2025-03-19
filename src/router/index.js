import { createWebHistory, createRouter } from 'vue-router'

import Register from '../pages/auth/Register.vue'
import Login from '../pages/auth/Login.vue'
import ApplyPage from '../pages/ApplyPage.vue'
import ApplicationList from '../pages/ApplicationLists/ApplicationList.vue'
import ApplicationDetails from '../pages/ApplicationLists/ApplicationDetails.vue'

const routes = [
  { path: '/sign-up', component: Register },
  { path: '/sign-in', component: Login },
  { path: '/apply_form', component: ApplyPage},
  { path: '/applications', name: 'ApplicationList', component: ApplicationList},
  { path: '/applications/:id',  name: 'ApplicationDetails', component: ApplicationDetails, props: true},
  { 
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('../pages/NotFound.vue')
},
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;