import{ defineStore } from 'pinia'
import {ref} from "vue";
export const User = defineStore('user',() => {
    const userName = ref('')
    const userId = ref( 0)
    return{userName,userId}
})