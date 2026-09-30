package com.kierbob.fantasyarmor.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import com.kierbob.fantasyarmor.FantasyArmor;

/**
 * A closed, flat-topped great helm. It is a humanoid model where only the head has cubes,
 * so it can be posed like any other helmet. Texture: textures/entity/great_helm.png (64x64).
 */
public final class GreatHelmModel {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(FantasyArmor.id("great_helm"), "main");

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
				// the bucket: a little taller than the vanilla helmet
				.texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(1.0F))
				// flat top plate with a lip, riveted around the edge
				.texOffs(0, 17).addBox(-5.5F, -10.5F, -5.5F, 11.0F, 1.0F, 11.0F)
				// brow plate above the eye slit
				.texOffs(0, 29).addBox(-5.5F, -7.0F, -5.5F, 11.0F, 1.0F, 1.0F)
				// glowing bar: up the face, over the top and down the back
				.texOffs(0, 31).addBox(-1.0F, -11.0F, -6.0F, 2.0F, 1.0F, 12.0F)
				.texOffs(48, 0).addBox(-1.0F, -10.0F, -6.0F, 2.0F, 11.0F, 1.0F)
				.texOffs(54, 0).addBox(-1.0F, -10.0F, 5.0F, 2.0F, 11.0F, 1.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		// HumanoidModel expects the rest of the body to exist; leave those parts empty.
		head.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	private GreatHelmModel() {
	}
}
