# Play Physics World Template

A template project for playing and enjoying physics world!

# Setup

1. Download [AndEngine](https://github.com/nicolasgramlich/AndEngine/tree/GLES2-AnchorCenter)
1. Download [AndEnginePhysicsBox2DExtension](https://github.com/nicolasgramlich/AndEnginePhysicsBox2DExtension/tree/GLES2-AnchorCenter)
1. Download [AndEngineDebugDrawExtension](https://github.com/nicolasgramlich/AndEnginePhysicsBox2DExtension/tree/GLES2-AnchorCenter)
1. Of course download [me](https://github.com/yohpapa/playphysicstemplate) !

Import all of the above projects to your Eclipse workspace.

# Usage (How to play)

You can use MainActivity in PlayPhysicsTemplate project. Of course you can also create your own Activity from scratch. Then you need to extend your Activity from PlayPhysicsTemplateActivity class in the project. In all cases, all you have to do is only to override and implement setupYourPhysicsWorld method. For example:

```java
	@Override
	protected void setupYourPhysicsWorld(PhysicsWorld world) {
		
		FixtureDef def = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
		
		// Left, bottom, right, top side walls
		PhysicsFactory.createLineBody(world, 2, 2, 2, HEIGHT - 2, def);
		PhysicsFactory.createLineBody(world, 2, 2, WIDTH - 2, 2, def);
		PhysicsFactory.createLineBody(world, WIDTH - 2, 2, WIDTH - 2, HEIGHT - 2, def);
		PhysicsFactory.createLineBody(world, WIDTH - 2, HEIGHT - 2, 2, HEIGHT - 2, def);
		
		// Boxes
		PhysicsFactory.createBoxBody(world, WIDTH / 2, HEIGHT * 2 / 3, 50, 50, BodyType.DynamicBody, def);
		
		// Balls
		PhysicsFactory.createCircleBody(world, WIDTH / 2 + getRandomNumber(), HEIGHT / 3, 25, BodyType.DynamicBody, def);
	}
```

That's all! A screenshot is here:

![Screenshot](https://raw.githubusercontent.com/yohpapa/playphysicstemplate/master/screenshot.png)

Let's have fun!

# License

Copyright 2014 Kensuke Nakai <kemumaki.kemuo@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
