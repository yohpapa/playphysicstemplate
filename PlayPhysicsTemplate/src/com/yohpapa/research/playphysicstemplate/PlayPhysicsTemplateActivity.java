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

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.debugdraw.DebugRenderer;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;

public abstract class PlayPhysicsTemplateActivity extends SimpleBaseGameActivity implements IAccelerationListener {

	private static final String TAG = PlayPhysicsTemplateActivity.class.getSimpleName();
	
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 480;
	private static final int FPS = 60;
	
	private PhysicsWorld _world = null;
	private Scene _scene;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		return new EngineOptions(
				true,
				ScreenOrientation.LANDSCAPE_FIXED,
				new FillResolutionPolicy(),
				new Camera(0, 0, WIDTH, HEIGHT)).setWakeLockOptions(WakeLockOptions.SCREEN_ON);
	}

	@Override
	protected void onCreateResources() throws IOException {}

	@Override
	protected Scene onCreateScene() {
		
		if(_scene == null) {
			_scene = new Scene();
			_scene.setBackground(new Background(0f, 0f, 0f));
			
			_world = new FixedStepPhysicsWorld(FPS, new Vector2(0, SensorManager.GRAVITY_EARTH), false);
			_scene.registerUpdateHandler(_world);
			
			setupYourPhysicsWorld(_world);
			
			DebugRenderer renderer = new DebugRenderer(_world, getVertexBufferObjectManager());
			_scene.attachChild(renderer);
		}
		
		return _scene;
	}
	
	@Override
	protected synchronized void onResume() {
		super.onResume();
		
		boolean result = enableAccelerationSensor(this);
		if(!result) {
			Debug.e(TAG, "Calling enableAccelerationSensor() has failed.");
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		boolean result = disableAccelerationSensor();
		if(!result) {
			Debug.e(TAG, "Calling disableAccelerationSensor() has failed.");
		}
	}
	
	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {}
	
	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		final Vector2 gravity = Vector2Pool.obtain(pAccelerationData.getX(), pAccelerationData.getY());
		_world.setGravity(gravity);
		Vector2Pool.recycle(gravity);
	}
	
	protected abstract void setupYourPhysicsWorld(PhysicsWorld world);
}
