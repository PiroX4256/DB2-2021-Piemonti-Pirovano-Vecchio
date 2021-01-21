import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login";
import Home from "../views/Home";
import Register from "../components/Register";
import AdminLogin from "../components/AdminLogin";
import AdminHomePage from "../components/AdminHomePage";
import AdminFormCreation from "../components/AdminFormCreation";
import AdminInspectQuestionnaire from "../components/AdminInspectQuestionnaire";
import AdminDeleteQuestionnaire from "../components/AdminDeleteQuestionnaire";
import QuestionnairePage from "../components/QuestionnairePage";
import InspectQuestionnaireById from "../components/InspectQuestionnaireById";
import Scoreboard from "@/components/Scoreboard";
import GreetingsPage from "../components/GreetingsPage";

Vue.use(VueRouter);

const routes = [
    { path: '/login', name: 'Login', component: Login },
    { path: '/', name: 'Home', component: Home },
    { path: '/register', name: 'Register', component: Register },
    { path: '/admin', name: 'Admin', component: AdminLogin },
    { path: '/adminHomePage', name: 'AdminHomePage', component: AdminHomePage },
    { path: '/adminCreateQuestionnaire', name: 'AdminCreateQuestionnaire', component: AdminFormCreation },
    { path: '/adminInspectQuestionnaire', name: 'AdminInspectQuestionnaire', component: AdminInspectQuestionnaire },
    { path: '/inspect/:id', name: 'AdminInspectId', component: InspectQuestionnaireById },
    { path: '/adminDeleteQuestionnaire', name: 'AdminDeleteQuestionnaire', component: AdminDeleteQuestionnaire },
    { path: '/fill/:date', name:'FillQuestionnaire', component: QuestionnairePage, props: true },
    { path: '/leaderboard', name: 'Leaderboard', component: Scoreboard },
    { path: '/greetings', name: 'GreetingsPage', component: GreetingsPage }

]

const router = new VueRouter({
    mode: "history",
    routes // short for `routes: routes`
});

export default router;
