import VueRouter from "vue-router";

const routes = [
    {
        path:'/',
        name:'Welcome',
        component:()=>import('../components/Welcome')

    },
    {
        path: '/Index',
        name: 'Index',
        component: () => import('../components/Index')
    },
    {
                path:'/House',
                name:'House',
                component:()=>import('../components/House.vue')
            },
            {
                path:'/self',
                name:'self',
                component:()=>import('../components/self.vue')
    },
    {
        path: '/Register',
        name:'Register',
        component:()=>import('../components/Register.vue')
    },
    {
        path: '/PerIfo',
        name:'PerIfo',
        component:()=>import('../components/PerIfo.vue')
    },
    {
        path: '/self',
        name:'self',
        component:()=>import('../components/self.vue')
    },
    {
        path: '/MyHouse',
        name:'MyHouse',
        component:()=>import('../components/MyHouse.vue')
    },
    {
        path: '/self2',
        name:'self2',
        component:()=>import('../components/self2.vue')
    },{
        path: '/Index2',
        name: 'Index2',
        component: () => import('../components/Index2')
    },
    {
        path: '/Contract',
        name: 'Contract',
        component: () => import('../components/Contract.vue')
    },
    {
        path: '/PerIfo2',
        name: 'PerIfo2',
        component: () => import('../components/PerIfo2.vue')
    }

]

const router = new VueRouter(
    {
        mode:'history',
        routes
    }

)

export default router;