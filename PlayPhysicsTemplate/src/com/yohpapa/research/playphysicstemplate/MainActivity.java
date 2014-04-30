/**
 * Copyright 2014 Kensuke Nakai<kemumaki.kemuo@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yohpapa.research.playphysicstemplate;

import java.util.Random;

import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MainActivity extends PlayPhysicsTemplateActivity {

	@Override
	protected void setupYourPhysicsWorld(PhysicsWorld world) {
		
		FixtureDef def = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
		
		// Left, bottom, right, top side walls
		PhysicsFactory.createLineBody(world, 2, 2, 2, HEIGHT - 2, def);
		PhysicsFactory.createLineBody(world, 2, 2, WIDTH - 2, 2, def);
		PhysicsFactory.createLineBody(world, WIDTH - 2, 2, WIDTH - 2, HEIGHT - 2, def);
		PhysicsFactory.createLineBody(world, WIDTH - 2, HEIGHT - 2, 2, HEIGHT - 2, def);
		
		// Boxes
		for(int i = 0; i < 10; i ++) {
			PhysicsFactory.createBoxBody(
								world,
								WIDTH / 2 + getRandomNumber() * i, HEIGHT * 2 / 3, 50, 50,
								BodyType.DynamicBody,
								def);
		}
		
		// Balls
		for(int i = 0; i < 10; i ++) {
			PhysicsFactory.createCircleBody(
								world,
								WIDTH / 2 + getRandomNumber() * i, HEIGHT / 3, 25,
								BodyType.DynamicBody,
								def);
		}
	}
	
	private int getRandomNumber() {
		Random rand = new Random(System.currentTimeMillis());
		int number = rand.nextInt(10);
		if(number % 2 == 0) {
			return -1 * number;
		} else {
			return number;
		}
	}
}