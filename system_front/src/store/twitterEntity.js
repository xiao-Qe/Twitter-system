import{ defineStore } from 'pinia'
import {ref} from "vue";
export const twitterEntity = defineStore('entity',() => {
    const title = ref('')//标题
    const author = ref('')//作者
    const blurb = ref('')//简介
    const content = ref('')//内容
    const like = ref(0)//喜欢
    const collect = ref(0)//收藏
    return {title,author,blurb,content,like,collect}
})