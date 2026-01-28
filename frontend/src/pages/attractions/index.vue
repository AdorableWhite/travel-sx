<template>
  <view class="container">
    <SectionTitle title="三晋览胜" extra="热门景点" />
    <InfoCard
      v-for="item in attractions"
      :key="item.id"
      :title="item.name"
      :subtitle="item.city"
      @click="goDetail(item.id)"
    >
      <view class="card-desc">{{ item.intro }}</view>
    </InfoCard>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchAttractions } from '../../services/api';
import { mockAttractions } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const attractions = ref(mockAttractions);

const load = async () => {
  try {
    const res = await fetchAttractions(10);
    if (res && res.data) {
      attractions.value = res.data;
    }
  } catch (e) {
    attractions.value = mockAttractions;
  }
};

const goDetail = (id) => {
  uni.navigateTo({ url: `/pages/attractions/detail?id=${id}` });
};

onMounted(load);
</script>

<style scoped lang="scss">
.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
