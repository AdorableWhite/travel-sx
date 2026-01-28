<template>
  <view class="container">
    <view class="profile">
      <view class="avatar">{{ profile.nickname ? profile.nickname.charAt(0) : '晋' }}</view>
      <view>
        <view class="name">{{ profile.nickname || '游客' }}</view>
        <view class="city">{{ profile.city || '山西' }}</view>
      </view>
    </view>

    <view class="section">
      <SectionTitle title="我的行程" />
      <InfoCard title="足迹地图" subtitle="基于行程自动生成"></InfoCard>
    </view>

    <view class="section">
      <SectionTitle title="收藏与点赞" />
      <InfoCard title="收藏" :subtitle="`共 ${favorites.length} 条`"></InfoCard>
      <InfoCard title="点赞" :subtitle="`共 ${likes.length} 条`"></InfoCard>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchUserProfile, fetchUserFavorites, fetchUserLikes } from '../../services/api';
import { mockUser } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const profile = ref(mockUser);
const favorites = ref([]);
const likes = ref([]);

const load = async () => {
  try {
    const res = await fetchUserProfile(1);
    if (res && res.data) {
      profile.value = res.data;
    }
    const favRes = await fetchUserFavorites(1);
    const likeRes = await fetchUserLikes(1);
    favorites.value = favRes?.data || [];
    likes.value = likeRes?.data || [];
  } catch (e) {
    profile.value = mockUser;
  }
};

onMounted(load);
</script>

<style scoped lang="scss">
.profile {
  display: flex;
  align-items: center;
  gap: 12px;
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  margin-bottom: 16px;
}

.avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #9a2e2e;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
}

.name {
  font-size: 16px;
  font-weight: 600;
}

.city {
  font-size: 12px;
  color: #7a6f64;
}
</style>
