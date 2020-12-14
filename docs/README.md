### 接口文档

BASE_DOMAIN: 

#### Login



    path: 

    method: get
    params: {
      username: string;
      password: string;
    }

    response {
      code: number;
      token: string;
    }
