// history模式
import {
    createRouter, createWebHistory
} from 'vue-router'

const routes = [
    {
        path: "/",
        name: "Index",
        component: () =>
            import( "../components/Index.vue"),
        children: [
            {
                path: "/order/show",
                name: "OrderShow",
                component: () =>
                    import( "../components/orders/Show.vue")
            },
            {
                path: "/order/add",
                name: "OrderAdd",
                component: () =>
                    import( "../components/orders/Add.vue")
            },
            {
                path: "/order/update",
                name: "OrderUpdate",
                component: () =>
                    import( "../components/orders/Update.vue")
            },
            {
                path: "/log",
                name: "log",
                component: () =>
                    import( "../components/orders/log.vue")
            },
            {
                path: "/users/show",
                name: "UserShow",
                component: () =>
                    import( "../components/users/Show.vue")
            },
            {
                path: "/users/add",
                name: "UserAdd",
                component: () =>
                    import( "../components/users/Add.vue")
            },
            {
                path: "/users/update",
                name: "UserUpdate",
                component: () =>
                    import( "../components/users/Update.vue")
            },
            {
                path: "/roo/show",
                name: "RooShow",
                component: () =>
                    import( "../components/room/Show.vue")
            },
            {
                path: "/roo/add",
                name: "RooAdd",
                component: () =>
                    import( "../components/room/Add.vue")
            },
            {
                path: "/roo/update",
                name: "RooUpdate",
                component: () =>
                    import( "../components/room/Update.vue")
            }
        ]
    }
];

// 创建路由对象
const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router;
